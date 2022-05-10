package com.example.bugtracker.mapping;

import com.example.bugtracker.dto.ticket.CommentDto;
import com.example.bugtracker.dto.ticket.TicketDto;
import com.example.bugtracker.dto.user.UserCreateDto;
import com.example.bugtracker.model.Comment;
import com.example.bugtracker.model.Ticket;
import com.example.bugtracker.model.User;
import lombok.AllArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class MappingService {
    private final ModelMapper modelMapper;

    @PostConstruct
    public void initConfig() {
        userMap();
        ticketMap();
    }

    private void userMap() {
        modelMapper.addMappings(new PropertyMap<UserCreateDto, User>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                skip(destination.getPassword());
                skip(destination.getCompany());
                skip(destination.getRoles());
            }
        });
    }

    private void ticketMap() {
        modelMapper.typeMap(Ticket.class, TicketDto.class)
                .addMappings(mapper -> mapper
                        .using((Converter<User, String>) mappingContext ->
                                mappingContext.getSource() == null ? null :
                                        mappingContext.getSource().getEmail())
                        .map(Ticket::getReporter, TicketDto::setReporter));
        modelMapper.typeMap(Ticket.class, TicketDto.class)
                .addMappings(mapper -> mapper
                        .using((Converter<User, String>) mappingContext ->
                                mappingContext.getSource() == null ? null :
                                        mappingContext.getSource().getEmail())
                        .map(Ticket::getResponsible, TicketDto::setResponsible));
        modelMapper.typeMap(Comment.class, CommentDto.class)
                .addMappings(mapper -> mapper
                        .using((Converter<User, String>) mappingContext ->
                                mappingContext.getSource() == null ? null :
                                        mappingContext.getSource().getEmail())
                        .map(Comment::getAuthor, CommentDto::setAuthor));
    }

    public <S, D> void addConverter(Converter<S, D> converter) {
        modelMapper.addConverter(converter);
    }

    public <S, D> D map(S source, Class<D> clazz) {
        return modelMapper.map(source, clazz);
    }

    public <S, D> void map(S source, D dest) {
        modelMapper.map(source, dest);
    }

    public <S, D> List<D> mapList(List<S> sources, Class<D> clazz) {
        return sources.stream()
                .map(s -> map(s, clazz))
                .collect(Collectors.toList());
    }

    public <S, D> Page<D> mapPages(Page<S> page, Class<D> clazz) {
        return new PageImpl<>(mapList(page.getContent(), clazz), page.getPageable(), page.getTotalElements());
    }
}
