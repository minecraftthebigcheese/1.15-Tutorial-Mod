package com.thebigcheese.tutorialmod.init;

import com.thebigcheese.tutorialmod.TutorialMod;
import com.thebigcheese.tutorialmod.TutorialMod.TutorialItemGroup;
import com.thebigcheese.tutorialmod.objects.blocks.SpecalBlock;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(TutorialMod.MOD_ID)
@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Bus.MOD)
public class BlockInit 
{
	public static final Block example_block =null;
	public static final Block specal_block = null;
	
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) 
	{
		//custom block
		event.getRegistry().register(new SpecalBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(2.0F, 10.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.GLASS).lightValue(4).slipperiness(1.2F).speedFactor(0.7F).noDrops()).setRegistryName("specal_block"));
		
		//normal block
		event.getRegistry().register(new Block(Block.Properties.create(Material.ANVIL).hardnessAndResistance(0.5f, 15.0f).sound(SoundType.ANVIL)).setRegistryName("example_block"));
	}
	
	@SubscribeEvent
	public static void registerBlockItems(final RegistryEvent.Register<Item> event) 
	{
		//custom block
		event.getRegistry().register(new BlockItem(specal_block, new Item.Properties().group(TutorialItemGroup.instance)).setRegistryName("specal_block"));
		
		//normal block
		event.getRegistry().register(new BlockItem(example_block, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("example_block"));
	}
}
