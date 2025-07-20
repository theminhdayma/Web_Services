package com.data.session09.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.data.session09.model.dto.request.MovieDto;
import com.data.session09.model.entity.Movie;
import com.data.session09.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final Cloudinary cloudinary;

    private static final Logger log = LoggerFactory.getLogger(MovieService.class);

    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Phim không tồn tại với ID: " + id));
    }

    public Movie insertMovie(MovieDto movieDto) {
        try {
            log.debug("Đang xử lý thêm phim mới: {}", movieDto.getTitle());

            // Upload ảnh lên Cloudinary
            Map uploadResult = cloudinary.uploader().upload(movieDto.getPoster().getBytes(),
                    ObjectUtils.asMap("folder", "movies"));

            String posterUrl = (String) uploadResult.get("secure_url");

            Movie movie = Movie.builder()
                    .title(movieDto.getTitle())
                    .description(movieDto.getDescription())
                    .releaseDate(movieDto.getReleaseDate())
                    .poster(posterUrl)
                    .build();

            Movie savedMovie = movieRepository.save(movie);

            String currentTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    .format(LocalDateTime.now());

            log.info("Thêm phim thành công: {} vào lúc {}", savedMovie.getTitle(), currentTime);
            return savedMovie;
        } catch (IOException e) {
            log.error("Lỗi khi upload ảnh lên Cloudinary: {}", e.getMessage(), e);
            throw new RuntimeException("Upload ảnh thất bại.");
        } catch (Exception e) {
            log.error("Lỗi khi thêm phim: {}", e.getMessage(), e);
            throw new RuntimeException("Thêm phim thất bại.");
        }
    }

    public Movie updateMovie(Long id, MovieDto movieDto) {
        Movie existingMovie = getMovieById(id);

        try {
            log.debug("Đang xử lý cập nhật phim với ID: {}", id);

            // Upload ảnh mới nếu có
            String posterUrl = existingMovie.getPoster();
            if (movieDto.getPoster() != null && !movieDto.getPoster().isEmpty()) {
                Map uploadResult = cloudinary.uploader().upload(movieDto.getPoster().getBytes(),
                        ObjectUtils.asMap("folder", "movies"));
                posterUrl = (String) uploadResult.get("secure_url");
            }

            existingMovie.setTitle(movieDto.getTitle());
            existingMovie.setDescription(movieDto.getDescription());
            existingMovie.setReleaseDate(movieDto.getReleaseDate());
            existingMovie.setPoster(posterUrl);

            Movie updatedMovie = movieRepository.save(existingMovie);

            String currentTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    .format(LocalDateTime.now());

            log.info("Cập nhật phim thành công: {} vào lúc {}", updatedMovie.getTitle(), currentTime);
            return updatedMovie;
        } catch (IOException e) {
            log.error("Lỗi khi upload ảnh mới lên Cloudinary: {}", e.getMessage(), e);
            throw new RuntimeException("Cập nhật ảnh thất bại.");
        } catch (Exception e) {
            log.error("Lỗi khi cập nhật phim: {}", e.getMessage(), e);
            throw new RuntimeException("Cập nhật phim thất bại.");
        }
    }

    public void deleteMovie(Long id) {
        Movie movie = getMovieById(id);
        try {
            log.debug("Đang xử lý xóa phim với ID: {}", id);

            movieRepository.delete(movie);

            String currentTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    .format(LocalDateTime.now());

            log.info("Xóa phim thành công: {} vào lúc {}", movie.getTitle(), currentTime);
        } catch (Exception e) {
            log.error("Lỗi khi xóa phim: {}", e.getMessage(), e);
            throw new RuntimeException("Xóa phim thất bại.");
        }
    }

    public List<Movie> searchMoviesByTitle(String title) {
        log.debug("Đang tìm kiếm phim với tiêu đề: {}", title);
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Movie> getMovieSuggestions(String title) {
        log.debug("Đang lấy gợi ý phim cho tiêu đề: {}", title);
        return movieRepository.findTop5ByTitleContainingIgnoreCase(title);
    }
}
