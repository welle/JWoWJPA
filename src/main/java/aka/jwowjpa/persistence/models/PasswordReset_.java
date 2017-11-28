package aka.jwowjpa.persistence.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-11-28T13:48:59.851+0100")
@StaticMetamodel(PasswordReset.class)
public class PasswordReset_ {
	public static volatile SingularAttribute<PasswordReset, Long> id;
	public static volatile SingularAttribute<PasswordReset, Date> createdAt;
	public static volatile SingularAttribute<PasswordReset, String> email;
	public static volatile SingularAttribute<PasswordReset, String> token;
}
