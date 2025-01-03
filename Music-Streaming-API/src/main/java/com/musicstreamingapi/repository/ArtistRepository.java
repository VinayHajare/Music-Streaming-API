package com.musicstreamingapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musicstreamingapi.model.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long>{
	List<Artist> findByNameContainingIgnoreCase(String name);
}
