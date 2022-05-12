package eus.birt.ad7.repository;

import eus.birt.ad7.domain.Crag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CragRepository extends JpaRepository<Crag, Long> {
}
