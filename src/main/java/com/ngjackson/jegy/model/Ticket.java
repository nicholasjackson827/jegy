package com.ngjackson.jegy.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
public class Ticket {

  private Long id;
  private String summary;
  private String body;
  private User requester;
  private User assignee;

}
