package com.sample.service;

import java.util.List;

import com.sample.dto.AlbumDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sample.db.entity.AlbumEntity;

public interface IAlbumService {

    long countAll() throws Exception;

    AlbumDTO save(AlbumDTO dto) throws Exception;

    AlbumDTO update(AlbumDTO dto) throws Exception;

    void delete(AlbumDTO dto) throws Exception;

    void deleteByPk(Integer pk) throws Exception;

    AlbumDTO findByPk(Integer pk) throws Exception;

    List<AlbumDTO> findAll() throws Exception;

    Page<AlbumDTO> findAll(Pageable pageable) throws Exception;

}
