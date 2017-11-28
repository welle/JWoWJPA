package aka.jwowjpa.persistence.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-11-28T13:48:59.858+0100")
@StaticMetamodel(Spell.class)
public class Spell_ {
	public static volatile SingularAttribute<Spell, Long> id;
	public static volatile SingularAttribute<Spell, byte[]> icon;
	public static volatile SingularAttribute<Spell, Long> idWoW;
	public static volatile SingularAttribute<Spell, String> name;
	public static volatile SingularAttribute<Spell, String> nameEN;
	public static volatile ListAttribute<Spell, Item> items;
	public static volatile ListAttribute<Spell, Mount> mounts;
}
