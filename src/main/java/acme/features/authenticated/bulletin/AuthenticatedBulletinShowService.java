
package acme.features.authenticated.bulletin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.util.concurrent.AbstractService;

import acme.entities.bulletin.Bulletin;
import acme.framework.components.accounts.Authenticated;

@Service
public class AuthenticatedBulletinShowService extends AbstractService<Authenticated, Bulletin> {

	@Autowired
	protected AuthenticatedBulletinRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {

		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Bulletin object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findBulletinById(id);

		super.getBuffer().setData(object);
	}

	@Override
	public void unbind(final Bulletin object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "title", "instantiation", "message$", "flag", "link");

		super.getResponse().setData(tuple);
	}
}
