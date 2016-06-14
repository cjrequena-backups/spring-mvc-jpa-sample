package com.sample.db.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sample.db.entity.AlbumEntity;

public interface AlbumRepository extends PagingAndSortingRepository<AlbumEntity, Integer> {

	//Page<AlbumEntity> findAll(Pageable pageable);
}
