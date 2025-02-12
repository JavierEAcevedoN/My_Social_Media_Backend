package c3.msmb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import c3.msmb.model.Like;
import jakarta.transaction.Transactional;

import java.util.List;


public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByIdPublicationId(Long idPublication);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO public.\"like\" (username, id_publication, liked) VALUES " +
        "(:username, :idPublication, CURRENT_DATE) " +
        "ON CONFLICT (username, id_publication) DO NOTHING", nativeQuery = true)
    int like(@Param("username") String username, @Param("idPublication") Long idPublication);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM public.\"like\" WHERE username = :username AND id_publication = :idPublication", 
        nativeQuery = true)
    int unlike(@Param("username") String username, @Param("idPublication") Long idPublication);

    boolean existsByUsernameUsernameAndIdPublicationId(String username, Long idPublication);
}