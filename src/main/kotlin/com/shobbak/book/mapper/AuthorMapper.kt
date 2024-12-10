package com.shobbak.book.mapper

import com.shobbak.book.dto.AuthorDto
import com.shobbak.book.entity.Author
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.springframework.stereotype.Component

@Mapper(componentModel = "spring")
interface AuthorMapper {
    @Mapping(target = "id", ignore = true) // Ignore ID during mapping
//    @Mapping(target = "createdAt", ignore = true) //
    fun toEntity(author: AuthorDto): Author
}
