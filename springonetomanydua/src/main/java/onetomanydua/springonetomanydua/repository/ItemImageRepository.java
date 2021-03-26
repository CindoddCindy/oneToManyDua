package onetomanydua.springonetomanydua.repository;

import onetomanydua.springonetomanydua.model.ItemImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemImageRepository extends JpaRepository<ItemImage, String> {

    Page<ItemImage> findByPostId(Long postId, Pageable pageable);
}
