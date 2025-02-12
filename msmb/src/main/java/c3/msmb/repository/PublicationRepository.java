package c3.msmb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import c3.msmb.model.Publication;
import jakarta.transaction.Transactional;
import java.util.List;


public interface PublicationRepository extends JpaRepository<Publication, Long> {
        List<Publication> findByUsernameUsername(String username);

        @Modifying
        @Transactional
        @Query(value = "UPDATE public.publication SET " +
                "content = COALESCE(:content, content), " +
                "img_src = COALESCE(:imgSrc, img_src), " +
                "tags = COALESCE(:tags, tags), " +
                "updated = CURRENT_TIMESTAMP " +
                "WHERE id = :id", 
        nativeQuery = true)
        int updatePartialPublication(
                @Param("content") String content,
                @Param("imgSrc") String imgSrc,
                @Param("tags") String[] tags,
                @Param("id") Long id);
}