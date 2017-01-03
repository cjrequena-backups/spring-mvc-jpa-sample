package com.sample.mapper;

import com.sample.db.entity.AlbumEntity;
import com.sample.dto.AlbumDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {
    ArtistMapper.class,
})
public interface AlbumMapper extends DTOEntityMapper<AlbumDTO, AlbumEntity> {

    AlbumMapper INSTANCE = Mappers.getMapper(AlbumMapper.class);

}
