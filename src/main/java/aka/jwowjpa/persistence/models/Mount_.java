package aka.jwowjpa.persistence.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-11-28T13:48:59.845+0100")
@StaticMetamodel(Mount.class)
public class Mount_ {
	public static volatile SingularAttribute<Mount, Long> id;
	public static volatile SingularAttribute<Mount, byte[]> icon;
	public static volatile SingularAttribute<Mount, Long> idCreature;
	public static volatile SingularAttribute<Mount, Boolean> isAquatic;
	public static volatile SingularAttribute<Mount, Boolean> isFlying;
	public static volatile SingularAttribute<Mount, Boolean> isGround;
	public static volatile SingularAttribute<Mount, Boolean> isJumping;
	public static volatile SingularAttribute<Mount, String> name;
	public static volatile SingularAttribute<Mount, String> nameEN;
	public static volatile SingularAttribute<Mount, String> quality;
	public static volatile SingularAttribute<Mount, Item> item;
	public static volatile SingularAttribute<Mount, Spell> spell;
}
