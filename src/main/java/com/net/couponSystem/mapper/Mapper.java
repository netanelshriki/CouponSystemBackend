package com.net.couponSystem.mapper;

import java.util.List;

public interface Mapper <DAO,DTO>{
    DAO toDao(DTO dto) throws Exception;
    DTO toDto(DAO dao);
    List<DAO> toDaoList(List<DTO> dtos) throws Exception;
    List<DTO> toDtoList(List<DAO> daos);
}