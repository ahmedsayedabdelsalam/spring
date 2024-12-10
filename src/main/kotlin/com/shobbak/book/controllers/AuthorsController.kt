package com.shobbak.book.controllers

import com.shobbak.book.dto.AuthorDto
import com.shobbak.book.entity.Author
import com.shobbak.book.mapper.AuthorMapper
import com.shobbak.book.repos.AuthorRepo
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("authors")
class AuthorsController(
    private val authorMapper: AuthorMapper,
    val authorRepo: AuthorRepo) {


    @GetMapping("")
    fun index(
        @RequestParam(name = "page", defaultValue = "1") page: Int,
        @RequestParam(name = "size", defaultValue = "10") size: Int,
    ): Page<Author> {
//        return authorRepo.findAll(PageRequest.of(page - 1, size));
        return authorRepo.fetchAuthorsWithBooks(PageRequest.of(page -1,size))

    }

    @PostMapping("")
    fun create(
        @Valid @RequestBody authDto: AuthorDto
    ): Author {
        return authorRepo.save(
            authorMapper.toEntity(authDto)
        )
    }
}
