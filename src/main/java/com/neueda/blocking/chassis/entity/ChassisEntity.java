package com.neueda.blocking.chassis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="chassis_entity")
public class ChassisEntity {

    @TableGenerator(name="Chassis_Gen", initialValue = 1)
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE, generator = "Chassis_Gen")
    private Long id;
    private String name;
    private String description;


}
