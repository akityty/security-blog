package konkon.repository;

import konkon.model.Blog;
import konkon.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BlogRepository extends PagingAndSortingRepository<Blog, Long> {
  Page<Blog> findAllByTittleContaining(String name, Pageable pageable);

  @Query("select  b from Blog  b order by  b.id desc ")
  Page<Blog> sort(Pageable pageable);

  Iterable<Blog> findAllByCategory(Category category);
}

