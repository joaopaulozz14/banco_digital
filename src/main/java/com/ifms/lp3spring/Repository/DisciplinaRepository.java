package com.ifms.lp3spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ifms.lp3spring.model.DisciplinaModel;

@Repository
public interface DisciplinaRepository extends JpaRepository<DisciplinaModel, Long> {
    
}
