package sdkdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sdkdata.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    Project findFirstByProjectId(String projectId);
    Project findTopByOrderByIdDesc();
}
