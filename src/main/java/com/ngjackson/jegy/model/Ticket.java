package com.ngjackson.jegy.model;

import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Transactional
public class Ticket {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String summary;
  private String body;

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "requester_id")
  private User requester;

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "assignee_id")
  private User assignee;

}
