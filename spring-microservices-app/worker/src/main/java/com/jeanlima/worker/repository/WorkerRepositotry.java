package com.jeanlima.worker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeanlima.worker.entities.Worker;

public interface WorkerRepositotry extends JpaRepository<Worker,Long>{
    
}
