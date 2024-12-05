package com.musicstreamingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musicstreamingapi.model.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long>{
	
}
