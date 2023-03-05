
package acme.entities.tutorial;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.data.AbstractEntity;

public class Assistant extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Length(max = 76)
	protected String			supervisor;

	@NotBlank
	@Length(max = 101)
	protected String			expertiseFields;

	@NotBlank
	@Length(max = 101)
	protected String			resume;

	@URL
	protected String			furtherInformation;

}
