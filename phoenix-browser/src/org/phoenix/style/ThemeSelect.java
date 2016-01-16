package org.phoenix.style;

import javax.swing.LookAndFeel;

import org.jvnet.substance.skin.SubstanceAutumnLookAndFeel;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;
import org.jvnet.substance.skin.SubstanceBusinessBlueSteelLookAndFeel;
import org.jvnet.substance.skin.SubstanceBusinessLookAndFeel;
import org.jvnet.substance.skin.SubstanceChallengerDeepLookAndFeel;
import org.jvnet.substance.skin.SubstanceCremeCoffeeLookAndFeel;
import org.jvnet.substance.skin.SubstanceCremeLookAndFeel;
import org.jvnet.substance.skin.SubstanceEmeraldDuskLookAndFeel;
import org.jvnet.substance.skin.SubstanceFieldOfWheatLookAndFeel;
import org.jvnet.substance.skin.SubstanceGreenMagicLookAndFeel;
import org.jvnet.substance.skin.SubstanceMagmaLookAndFeel;
import org.jvnet.substance.skin.SubstanceMangoLookAndFeel;
import org.jvnet.substance.skin.SubstanceMistAquaLookAndFeel;
import org.jvnet.substance.skin.SubstanceMistSilverLookAndFeel;
import org.jvnet.substance.skin.SubstanceModerateLookAndFeel;
import org.jvnet.substance.skin.SubstanceNebulaBrickWallLookAndFeel;
import org.jvnet.substance.skin.SubstanceNebulaLookAndFeel;
import org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel;
import org.jvnet.substance.skin.SubstanceOfficeSilver2007LookAndFeel;
import org.jvnet.substance.skin.SubstanceRavenGraphiteGlassLookAndFeel;
import org.jvnet.substance.skin.SubstanceRavenGraphiteLookAndFeel;
import org.jvnet.substance.skin.SubstanceRavenLookAndFeel;
import org.jvnet.substance.skin.SubstanceSaharaLookAndFeel;

/**
 * 类描述：封装框架支持的主题样式
 * @author mengfeiyang
 * @version 1.0
 * 日期：2013年8月9日11:21
 */

public class ThemeSelect {
	public static LookAndFeel getTheme(Theme theme){
		switch(theme){
		case Autumn:
			return new SubstanceAutumnLookAndFeel();
		case BusinessBlackSteel:
			return new SubstanceBusinessBlackSteelLookAndFeel();
		case BusinessBlueSteel:
			return new SubstanceBusinessBlueSteelLookAndFeel();
		case Business:
			return new SubstanceBusinessLookAndFeel();
		case ChallengerDeep:
			return new SubstanceChallengerDeepLookAndFeel();
		case CremeCoffee:
			return new SubstanceCremeCoffeeLookAndFeel();
		case Creme:
			return new SubstanceCremeLookAndFeel();
		case EmeraldDusk:
			return new SubstanceEmeraldDuskLookAndFeel();
		case FieldOfWheat:
			return new SubstanceFieldOfWheatLookAndFeel();
		case GreenMagic:
			return new SubstanceGreenMagicLookAndFeel();
		case Magma:
			return new SubstanceMagmaLookAndFeel();
		case Mango:
			return new SubstanceMangoLookAndFeel();
		case MistAqua:
			return new SubstanceMistAquaLookAndFeel();
		case MistSilver:
			return new SubstanceMistSilverLookAndFeel();
		case Moderate:
			return new SubstanceModerateLookAndFeel();
		case NebulaBrickWall:
			return new SubstanceNebulaBrickWallLookAndFeel();
		case Nebula:
			return new SubstanceNebulaLookAndFeel();
		case OfficeBlue2007:
			return new SubstanceOfficeBlue2007LookAndFeel();
		case OfficeSilver2007:
			return new SubstanceOfficeSilver2007LookAndFeel();
		case RavenGraphiteGlass:
			return new SubstanceRavenGraphiteGlassLookAndFeel();
		case RavenGraphite:
			return new SubstanceRavenGraphiteLookAndFeel();
		case Raven:
			return new SubstanceRavenLookAndFeel();
		case Sahara:
			return new SubstanceSaharaLookAndFeel();

		default:;
		}
		return null;
	}
	
	public static String getTheme(String theme){
		switch(theme){
		case "Windows":
			return "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		case "WindowsClassic":
			return "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
		case "Nimbus":
			return "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
		case "Basic":
			return "javax.swing.plaf.basic.BasicLookAndFeel";
		case "Synth":
			return "javax.swing.plaf.synth.SynthLookAndFeel";
		case "Multi":
			return "javax.swing.plaf.multi.MultiLookAndFeel";
		case "Metal":
			return "javax.swing.plaf.metal.MetalLookAndFeel";
		default:;
		}
		return null;
	}

}
