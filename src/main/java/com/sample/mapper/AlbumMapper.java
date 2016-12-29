package com.sample.mapper;

import com.sample.db.entity.AlbumEntity;
import com.sample.dto.AlbumDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created by crequena on 29/12/2016.
 */
@Mapper
public interface AlbumMapper extends DTOEntityMapper<AlbumDTO, AlbumEntity> {

    AlbumMapper INSTANCE = Mappers.getMapper(AlbumMapper.class);

    @Override
    AlbumDTO toDto(AlbumEntity entity);

    @Override
    AlbumEntity toEntity(AlbumDTO dto);

    @Override
    List<AlbumDTO> toDtos(List<AlbumEntity> entities);

    @Override
    List<AlbumEntity> toEntities(List<AlbumDTO> dtos);
}
