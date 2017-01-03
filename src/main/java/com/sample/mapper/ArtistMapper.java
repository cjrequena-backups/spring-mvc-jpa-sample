package com.sample.mapper;

import com.sample.db.entity.AlbumEntity;
import com.sample.db.entity.ArtistEntity;
import com.sample.dto.AlbumDTO;
import com.sample.dto.ArtistDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {
})
public interface ArtistMapper extends DTOEntityMapper<ArtistDTO, ArtistEntity> {

    ArtistMapper INSTANCE = Mappers.getMapper(ArtistMapper.class);

}
