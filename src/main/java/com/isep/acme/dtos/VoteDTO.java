package com.isep.acme.dtos;

import com.isep.acme.model.enumerate.voteType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class VoteDTO {
    
    private long voteId;
    private voteType voteType;
		private String user;
    private long idReview;
} 
