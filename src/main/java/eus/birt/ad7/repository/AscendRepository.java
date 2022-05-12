package eus.birt.ad7.repository;

import eus.birt.ad7.domain.Ascend;
import eus.birt.ad7.domain.AscendKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AscendRepository extends JpaRepository<Ascend, AscendKey> {
}
