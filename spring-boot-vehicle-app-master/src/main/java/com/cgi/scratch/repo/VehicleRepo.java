package com.cgi.scratch.repo;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.cgi.scratch.model.Vehicle;

@Repository
@Transactional
public interface VehicleRepo extends JpaRepository<Vehicle, Integer >{

}
