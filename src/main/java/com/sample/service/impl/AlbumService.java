package com.sample.service.impl;

import com.sample.db.entity.AlbumEntity;
import com.sample.db.repository.AlbumRepository;
import com.sample.dto.AlbumDTO;
import com.sample.mapper.AlbumMapper;
import com.sample.service.IAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service("albumService")
public class AlbumService implements IAlbumService, Serializable {

    @Autowired
    AlbumRepository albumRepository;

    @Override
    public long countAll() throws Exception {
        return this.albumRepository.count();
    }

    @Override
    public AlbumDTO save(AlbumDTO dto) throws Exception {
        AlbumEntity entity = this.albumRepository.findOne(dto.getAlbumId());
        if (entity != null) {
            throw new IllegalStateException("already.exists");
        }
        entity = AlbumMapper.INSTANCE.toEntity(dto);
        AlbumEntity entitySaved = this.albumRepository.save(entity);
        return AlbumMapper.INSTANCE.toDto(entitySaved);
    }

    @Override
    public AlbumDTO update(AlbumDTO dto) throws Exception {
        AlbumEntity entity = this.albumRepository.findOne(dto.getAlbumId());
        if (entity == null) {
            throw new IllegalStateException("does.not.exists");
        }
        dto.setAlbumId(entity.getAlbumId());
        return save(dto);
    }

    @Override
    public void delete(AlbumDTO dto) throws Exception {
        AlbumEntity entity = this.albumRepository.findOne(dto.getAlbumId());
        if (entity != null) {
            throw new IllegalStateException("does.not.exists");
        }
        this.albumRepository.delete(entity);
    }

    @Override
    public void deleteByPk(Integer pk) throws Exception {
        this.albumRepository.delete(pk);
    }

    @Override
    public AlbumDTO findByPk(Integer pk) throws Exception {
        AlbumEntity entity = this.albumRepository.findOne(pk);
        return AlbumMapper.INSTANCE.toDto(entity);
    }

    @Override
    public List<AlbumDTO> findAll() throws Exception {
        Iterable<AlbumEntity> entities = this.albumRepository.findAll();
        List<AlbumDTO> dtos = new ArrayList<AlbumDTO>();
        for (AlbumEntity entity : entities) {
            dtos.add(AlbumMapper.INSTANCE.toDto(entity));
        }
        return dtos;
    }

    @Override
    public Page<AlbumDTO> findAll(Pageable pageable) throws Exception {
        List<AlbumDTO> dtos = new ArrayList<AlbumDTO>();
        final Page<AlbumEntity> entityPage = this.albumRepository.findAll(pageable);
        for (AlbumEntity entity : entityPage) {
            dtos.add(AlbumMapper.INSTANCE.toDto(entity));
        }
        final PageImpl<AlbumDTO> dtoPage = new PageImpl<>(dtos, new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort()), entityPage.getTotalElements());
        return dtoPage;
    }
}
