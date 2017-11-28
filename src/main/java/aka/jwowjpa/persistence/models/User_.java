package aka.jwowjpa.persistence.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-11-28T13:48:59.865+0100")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, String> code;
	public static volatile SingularAttribute<User, Date> createdAt;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> name;
	public static volatile SingularAttribute<User, String> hashPassword;
	public static volatile SingularAttribute<User, String> rememberToken;
	public static volatile SingularAttribute<User, Date> updatedAt;
}
