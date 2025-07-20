package com.data.session09.controller;

import com.data.session09.model.entity.User;
import com.data.session09.exception.UserNotFoundException;
import com.data.session09.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    // Mock database
    private List<User> users = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            logger.info("Đang lấy danh sách tất cả users");

            // Giả lập có thể xảy ra lỗi
            if (users.isEmpty()) {
                logger.warn("Danh sách users rỗng");
            }

            logger.info("Lấy danh sách users thành công. Số lượng: {}", users.size());
            return ResponseEntity.ok(users);

        } catch (Exception e) {
            logger.error("Lỗi không mong muốn khi lấy danh sách users: {}", e.getMessage(), e);
            throw new RuntimeException("Lỗi hệ thống khi lấy danh sách users", e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        try {
            logger.info("Đang tìm user với ID: {}", id);

            // Validate input
            if (id == null || id <= 0) {
                logger.warn("ID không hợp lệ: {}", id);
                throw new ValidationException("ID phải là số dương");
            }

            Optional<User> user = users.stream()
                    .filter(u -> u.getId().equals(id))
                    .findFirst();

            if (user.isPresent()) {
                logger.info("Tìm thấy user với ID {}: {}", id, user.get().getName());
                return ResponseEntity.ok(user.get());
            } else {
                logger.warn("Không tìm thấy user với ID: {}", id);
                throw new UserNotFoundException("Không tìm thấy user với ID: " + id);
            }

        } catch (UserNotFoundException | ValidationException e) {
            // Re-throw business exceptions
            throw e;
        } catch (Exception e) {
            logger.error("Lỗi không mong muốn khi tìm user với ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Lỗi hệ thống khi tìm user", e);
        }
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            logger.info("Đang tạo user mới: {}", user.getName());

            validateUser(user);

            Long newId = users.stream()
                    .mapToLong(User::getId)
                    .max()
                    .orElse(0L) + 1;
            user.setId(newId);

            users.add(user);

            logger.info("Tạo user mới thành công với ID: {}", newId);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);

        } catch (ValidationException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Lỗi không mong muốn khi tạo user: {}", e.getMessage(), e);
            throw new RuntimeException("Lỗi hệ thống khi tạo user", e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            logger.info("Đang xóa user với ID: {}", id);

            if (id == null || id <= 0) {
                logger.warn("ID không hợp lệ khi xóa: {}", id);
                throw new ValidationException("ID phải là số dương");
            }

            boolean removed = users.removeIf(u -> u.getId().equals(id));

            if (removed) {
                logger.info("Xóa user thành công với ID: {}", id);
                return ResponseEntity.ok("Xóa user thành công");
            } else {
                logger.warn("Không tìm thấy user để xóa với ID: {}", id);
                throw new UserNotFoundException("Không tìm thấy user với ID: " + id);
            }

        } catch (UserNotFoundException | ValidationException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Lỗi không mong muốn khi xóa user với ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Lỗi hệ thống khi xóa user", e);
        }
    }

    @GetMapping("/simulate-error")
    public ResponseEntity<String> simulateError() {
        logger.info("Endpoint simulate-error được gọi");
        try {
            int result = 10 / 0;
            return ResponseEntity.ok("Kết quả: " + result);
        } catch (ArithmeticException e) {
            logger.error("Lỗi chia cho 0 trong simulate-error: {}", e.getMessage(), e);
            throw new RuntimeException("Lỗi tính toán", e);
        }
    }

    private void validateUser(User user) {
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            logger.warn("Validation failed: Tên user rỗng");
            throw new ValidationException("Tên không được để trống");
        }

        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            logger.warn("Validation failed: Email không hợp lệ: {}", user.getEmail());
            throw new ValidationException("Email không hợp lệ");
        }

        if (user.getAge() == null || user.getAge() < 0 || user.getAge() > 150) {
            logger.warn("Validation failed: Tuổi không hợp lệ: {}", user.getAge());
            throw new ValidationException("Tuổi phải từ 0 đến 150");
        }
    }
}
