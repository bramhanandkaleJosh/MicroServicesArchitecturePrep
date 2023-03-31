package dao;

import entities.Documents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentsDao extends JpaRepository<Documents, Long> {
}
