package com.example.prode.repositories;

import com.example.prode.models.Result;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ResultDto extends CrudRepository<Result, Long> {

    List<Result> getResultsById(Long id);

}
