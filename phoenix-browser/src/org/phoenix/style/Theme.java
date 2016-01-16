package org.phoenix.style;

/**
 * 类功能描述：枚举所有框架支持的主题样式
 * @author mengfeiyang
 * 日期：2013年8月9日
 */

public enum Theme implements ThemeName{
	Autumn,
	BusinessBlackSteel,
	BusinessBlueSteel,
	Business,
	ChallengerDeep,
	CremeCoffee,
	Creme,
	EmeraldDusk,
	FieldOfWheat,
	GreenMagic,
	Magma,
	Mango,
	MistAqua,
	MistSilver,
	Moderate,
	NebulaBrickWall,
	Nebula,
	OfficeBlue2007,
	OfficeSilver2007,
	RavenGraphiteGlass,
	RavenGraphite,
	Raven,
	Sahara,
	WindowsUI{
		public String getThemeName(){
			return "Windows";
		}
	},
	WindowsClassicUI{
		public String getThemeName(){
			return "WindowsClassic";
		}		
	},
	NimbusUI{
		public String getThemeName(){
			return "Nimbus";
		}
	},
	BasicUI{
		public String getThemeName(){
			return "Basic";
		}
	},
	MetalUI{
		public String getThemeName(){
			return "Metal";
		}
	},
	MultiUI{
		public String getThemeName(){
			return "Multi";
		}
	},
	SynthUI{
		public String getThemeName(){
			return "Synth";
		}
	},
	;

	@Override
	public String getThemeName() {
		// TODO Auto-generated method stub
		return null;
	}
}
