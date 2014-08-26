/**
 * 
 */
package org.minnal.dropwizard.examples.petclinic.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.activejpa.entity.Model;

/**
 * @author ganeshs
 *
 */
@MappedSuperclass
public class BaseDomain extends Model {
	
	private Long id;

	@Override
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}
