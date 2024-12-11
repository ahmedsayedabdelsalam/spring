package com.shobbak.book.mapper

import com.shobbak.book.dto.AuthorDto
import com.shobbak.book.dto.RegisterDto
import com.shobbak.book.entity.Author
import com.shobbak.book.entity.User
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.control.MappingControl.Use
import org.springframework.stereotype.Component

@Mapper(componentModel = "spring")
interface UserMapper {
    @Mapping(target = "id", ignore = true) // Ignore ID during mapping
    fun toEntity(user: RegisterDto): User

    fun toDto(user: User): RegisterDto
}
