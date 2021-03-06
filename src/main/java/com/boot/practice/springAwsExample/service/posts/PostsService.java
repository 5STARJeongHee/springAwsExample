package com.boot.practice.springAwsExample.service.posts;

import com.boot.practice.springAwsExample.domain.posts.Posts;
import com.boot.practice.springAwsExample.domain.posts.PostsRepository;
import com.boot.practice.springAwsExample.web.dto.PostsListResponseDto;
import com.boot.practice.springAwsExample.web.dto.PostsResponseDto;
import com.boot.practice.springAwsExample.web.dto.PostsSaveRequestDto;
import com.boot.practice.springAwsExample.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(("해당 게시글이 없습니다. id=" + id)));
        return new PostsResponseDto(entity);
    }

    @Transactional()
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        postsRepository.delete(posts);
    }
}
