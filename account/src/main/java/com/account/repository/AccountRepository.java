package com.account.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.account.model.request.AccontModel;
@Repository
public interface AccountRepository extends MongoRepository<AccontModel, String> {

}
