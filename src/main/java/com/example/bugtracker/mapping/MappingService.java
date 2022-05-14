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

/**
 * Service for mapping one class to other
 * with creating new object(s) and collection for boxing
 */

@AllArgsConstructor
@Service
public class MappingService {
    private final ModelMapper modelMapper;

    /**
     * Init mapping rules
     */
    @PostConstruct
    public void initConfig() {
        userMap();
        ticketMap();
    }

    /**
     * Mapping rules for user entity and its DTO
     */
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

    /**
     * Mapping rules for ticket entity and its DTO
     */
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

    /**
     * Map source object into destination class with
     * creating new object
     *
     * @param source source object
     * @param clazz destination class
     * @return new object of destination class
     */
    public <S, D> D map(S source, Class<D> clazz) {
        return modelMapper.map(source, clazz);
    }

    /**
     * Map source object into destination object
     *
     * @param source source object
     * @param dest destination object
     */
    public <S, D> void map(S source, D dest) {
        modelMapper.map(source, dest);
    }

    /**
     * Map list of source objects into destination class with
     * creating new objects and collection
     *
     * @param sources list of source objects
     * @param clazz destination class
     * @return new list of objects of destination class
     */
    public <S, D> List<D> mapList(List<S> sources, Class<D> clazz) {
        return sources.stream()
                .map(s -> map(s, clazz))
                .collect(Collectors.toList());
    }

    /**
     * Map page of source objects into destination class with
     * creating new objects and boxed page
     *
     * @param page page of source objects
     * @param clazz destination class
     * @return new page of object of destination class
     */
    public <S, D> Page<D> mapPages(Page<S> page, Class<D> clazz) {
        return new PageImpl<>(mapList(page.getContent(), clazz), page.getPageable(), page.getTotalElements());
    }
}
