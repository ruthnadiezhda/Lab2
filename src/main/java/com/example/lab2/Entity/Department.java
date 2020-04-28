package com.example.lab2.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="departments")
public class Department {

        @Id
        @Column(nullable = false)
        public Integer department_id;
        @Column(nullable = false)
        public String department_name;
        public String manager_id;
        public Integer location_id;
        @Column(nullable = false)
        public String department_short_name;


        public Department() {
        }

        public Integer getDepartment_id() {
            return department_id;
        }

        public void setDepartment_id(Integer department_id) {
            this.department_id = department_id;
        }

        public String getDepartment_name() {
            return department_name;
        }

        public void setDepartment_name(String department_name) {
            this.department_name = department_name;
        }

        public String getManager_id() {
            return manager_id;
        }

        public void setManager_id(String manager_id) {
            this.manager_id = manager_id;
        }

        public Integer getLocation_id() {
            return location_id;
        }

        public void setLocation_id(Integer location_id) {
            this.location_id = location_id;
        }

        public String getDepartment_short_name() {
            return department_short_name;
        }

        public void setDepartment_short_name(String department_short_name) {
            this.department_short_name = department_short_name;
        }
    }
