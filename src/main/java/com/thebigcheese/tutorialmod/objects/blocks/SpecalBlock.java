//directional block

package com.thebigcheese.tutorialmod.objects.blocks;

import java.util.stream.Stream;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class SpecalBlock extends Block
{
	
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	
	private static final VoxelShape SHAPE_N = Stream.of(
			Block.makeCuboidShape(4.999999999999998, 8, 13, 10.999999999999998, 9, 14),
			Block.makeCuboidShape(5, 8, 2, 11, 9, 3),
			Block.makeCuboidShape(11, 8, 12, 12, 9, 13),
			Block.makeCuboidShape(4, 8, 3, 5, 9, 4),
			Block.makeCuboidShape(4, 8, 12, 5, 9, 13),
			Block.makeCuboidShape(11, 8, 3, 12, 9, 4),
			Block.makeCuboidShape(12, 8, 11, 13, 9, 12),
			Block.makeCuboidShape(3, 8, 4, 4, 9, 5),
			Block.makeCuboidShape(3, 8, 11, 4, 9, 12),
			Block.makeCuboidShape(12, 8, 4, 13, 9, 5),
			Block.makeCuboidShape(2, 8, 5, 3, 9, 11),
			Block.makeCuboidShape(13, 8, 5, 14, 9, 11),
			Block.makeCuboidShape(6, 0, 6, 10, 9, 10),
			Block.makeCuboidShape(7, 7, 4, 9, 8, 6),
			Block.makeCuboidShape(10, 7, 7, 12, 8, 9),
			Block.makeCuboidShape(7, 7, 10, 9, 8, 12),
			Block.makeCuboidShape(4, 7, 7, 6, 8, 9),
			Block.makeCuboidShape(6, 9, 3, 10, 10, 4),
			Block.makeCuboidShape(6, 7, 3, 10, 8, 4),
			Block.makeCuboidShape(3, 9, 6, 4, 10, 10),
			Block.makeCuboidShape(3, 7, 6, 4, 8, 10),
			Block.makeCuboidShape(12, 9, 6, 13, 10, 10),
			Block.makeCuboidShape(12, 7, 6, 13, 8, 10),
			Block.makeCuboidShape(6, 9, 12, 10, 10, 13),
			Block.makeCuboidShape(6, 7, 12, 10, 8, 13),
			Block.makeCuboidShape(12, 9, 5, 13, 10, 6),
			Block.makeCuboidShape(12, 7, 5, 13, 8, 6),
			Block.makeCuboidShape(10, 9, 12, 11, 10, 13),
			Block.makeCuboidShape(10, 7, 12, 11, 8, 13),
			Block.makeCuboidShape(3, 9, 10, 4, 10, 11),
			Block.makeCuboidShape(3, 7, 10, 4, 8, 11),
			Block.makeCuboidShape(3, 9, 5, 4, 10, 6),
			Block.makeCuboidShape(3, 7, 5, 4, 8, 6),
			Block.makeCuboidShape(11, 9, 4, 12, 10, 5),
			Block.makeCuboidShape(11, 7, 4, 12, 8, 5),
			Block.makeCuboidShape(11, 9, 11, 12, 10, 12),
			Block.makeCuboidShape(11, 7, 11, 12, 8, 12),
			Block.makeCuboidShape(4, 9, 11, 5, 10, 12),
			Block.makeCuboidShape(4, 7, 11, 5, 8, 12),
			Block.makeCuboidShape(4, 9, 4, 5, 10, 5),
			Block.makeCuboidShape(4, 7, 4, 5, 8, 5),
			Block.makeCuboidShape(10, 9, 3, 11, 10, 4),
			Block.makeCuboidShape(10, 7, 3, 11, 8, 4),
			Block.makeCuboidShape(12, 9, 10, 13, 10, 11),
			Block.makeCuboidShape(12, 7, 10, 13, 8, 11),
			Block.makeCuboidShape(5, 9, 12, 6, 10, 13),
			Block.makeCuboidShape(5, 7, 12, 6, 8, 13),
			Block.makeCuboidShape(5, 9, 3, 6, 10, 4),
			Block.makeCuboidShape(5, 7, 3, 6, 8, 4),
			Block.makeCuboidShape(5, 8, 0, 11, 9, 10)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	private static final VoxelShape SHAPE_S = Stream.of(
			Block.makeCuboidShape(5.000000000000002, 8, 2, 11, 9, 3),
			Block.makeCuboidShape(5, 8, 13, 11, 9, 14),
			Block.makeCuboidShape(4, 8, 3, 5, 9, 4),
			Block.makeCuboidShape(11, 8, 12, 12, 9, 13),
			Block.makeCuboidShape(11, 8, 3, 12, 9, 4),
			Block.makeCuboidShape(4, 8, 12, 5, 9, 13),
			Block.makeCuboidShape(3, 8, 4, 4, 9, 5),
			Block.makeCuboidShape(12, 8, 11, 13, 9, 12),
			Block.makeCuboidShape(12, 8, 4, 13, 9, 5),
			Block.makeCuboidShape(3, 8, 11, 4, 9, 12),
			Block.makeCuboidShape(13, 8, 5, 14, 9, 11),
			Block.makeCuboidShape(2, 8, 5, 3, 9, 11),
			Block.makeCuboidShape(6, 0, 6, 10, 9, 10),
			Block.makeCuboidShape(7, 7, 10, 9, 8, 12),
			Block.makeCuboidShape(4, 7, 7, 6, 8, 9),
			Block.makeCuboidShape(7, 7, 4, 9, 8, 6),
			Block.makeCuboidShape(10, 7, 7, 12, 8, 9),
			Block.makeCuboidShape(6, 9, 12, 10, 10, 13),
			Block.makeCuboidShape(6, 7, 12, 10, 8, 13),
			Block.makeCuboidShape(12, 9, 6, 13, 10, 10),
			Block.makeCuboidShape(12, 7, 6, 13, 8, 10),
			Block.makeCuboidShape(3, 9, 6, 4, 10, 10),
			Block.makeCuboidShape(3, 7, 6, 4, 8, 10),
			Block.makeCuboidShape(6, 9, 3, 10, 10, 4),
			Block.makeCuboidShape(6, 7, 3, 10, 8, 4),
			Block.makeCuboidShape(3, 9, 10, 4, 10, 11),
			Block.makeCuboidShape(3, 7, 10, 4, 8, 11),
			Block.makeCuboidShape(5, 9, 3, 6, 10, 4),
			Block.makeCuboidShape(5, 7, 3, 6, 8, 4),
			Block.makeCuboidShape(12, 9, 5, 13, 10, 6),
			Block.makeCuboidShape(12, 7, 5, 13, 8, 6),
			Block.makeCuboidShape(12, 9, 10, 13, 10, 11),
			Block.makeCuboidShape(12, 7, 10, 13, 8, 11),
			Block.makeCuboidShape(4, 9, 11, 5, 10, 12),
			Block.makeCuboidShape(4, 7, 11, 5, 8, 12),
			Block.makeCuboidShape(4, 9, 4, 5, 10, 5),
			Block.makeCuboidShape(4, 7, 4, 5, 8, 5),
			Block.makeCuboidShape(11, 9, 4, 12, 10, 5),
			Block.makeCuboidShape(11, 7, 4, 12, 8, 5),
			Block.makeCuboidShape(11, 9, 11, 12, 10, 12),
			Block.makeCuboidShape(11, 7, 11, 12, 8, 12),
			Block.makeCuboidShape(5, 9, 12, 6, 10, 13),
			Block.makeCuboidShape(5, 7, 12, 6, 8, 13),
			Block.makeCuboidShape(3, 9, 5, 4, 10, 6),
			Block.makeCuboidShape(3, 7, 5, 4, 8, 6),
			Block.makeCuboidShape(10, 9, 3, 11, 10, 4),
			Block.makeCuboidShape(10, 7, 3, 11, 8, 4),
			Block.makeCuboidShape(10, 9, 12, 11, 10, 13),
			Block.makeCuboidShape(10, 7, 12, 11, 8, 13),
			Block.makeCuboidShape(5, 8, 6, 11, 9, 16)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	private static final VoxelShape SHAPE_E = Stream.of(
			Block.makeCuboidShape(2, 8, 5, 3, 9, 10.999999999999998),
			Block.makeCuboidShape(13, 8, 5, 14, 9, 11),
			Block.makeCuboidShape(3, 8, 11, 4, 9, 12),
			Block.makeCuboidShape(12, 8, 4, 13, 9, 5),
			Block.makeCuboidShape(3, 8, 4, 4, 9, 5),
			Block.makeCuboidShape(12, 8, 11, 13, 9, 12),
			Block.makeCuboidShape(4, 8, 12, 5, 9, 13),
			Block.makeCuboidShape(11, 8, 3, 12, 9, 4),
			Block.makeCuboidShape(4, 8, 3, 5, 9, 4),
			Block.makeCuboidShape(11, 8, 12, 12, 9, 13),
			Block.makeCuboidShape(5, 8, 2, 11, 9, 3),
			Block.makeCuboidShape(5, 8, 13, 11, 9, 14),
			Block.makeCuboidShape(6, 0, 6, 10, 9, 10),
			Block.makeCuboidShape(10, 7, 7, 12, 8, 9),
			Block.makeCuboidShape(7, 7, 10, 9, 8, 12),
			Block.makeCuboidShape(4, 7, 7, 6, 8, 9),
			Block.makeCuboidShape(7, 7, 4, 9, 8, 6),
			Block.makeCuboidShape(12, 9, 6, 13, 10, 10),
			Block.makeCuboidShape(12, 7, 6, 13, 8, 10),
			Block.makeCuboidShape(6, 9, 3, 10, 10, 4),
			Block.makeCuboidShape(6, 7, 3, 10, 8, 4),
			Block.makeCuboidShape(6, 9, 12, 10, 10, 13),
			Block.makeCuboidShape(6, 7, 12, 10, 8, 13),
			Block.makeCuboidShape(3, 9, 6, 4, 10, 10),
			Block.makeCuboidShape(3, 7, 6, 4, 8, 10),
			Block.makeCuboidShape(10, 9, 12, 11, 10, 13),
			Block.makeCuboidShape(10, 7, 12, 11, 8, 13),
			Block.makeCuboidShape(3, 9, 10, 4, 10, 11),
			Block.makeCuboidShape(3, 7, 10, 4, 8, 11),
			Block.makeCuboidShape(5, 9, 3, 6, 10, 4),
			Block.makeCuboidShape(5, 7, 3, 6, 8, 4),
			Block.makeCuboidShape(10, 9, 3, 11, 10, 4),
			Block.makeCuboidShape(10, 7, 3, 11, 8, 4),
			Block.makeCuboidShape(11, 9, 11, 12, 10, 12),
			Block.makeCuboidShape(11, 7, 11, 12, 8, 12),
			Block.makeCuboidShape(4, 9, 11, 5, 10, 12),
			Block.makeCuboidShape(4, 7, 11, 5, 8, 12),
			Block.makeCuboidShape(4, 9, 4, 5, 10, 5),
			Block.makeCuboidShape(4, 7, 4, 5, 8, 5),
			Block.makeCuboidShape(11, 9, 4, 12, 10, 5),
			Block.makeCuboidShape(11, 7, 4, 12, 8, 5),
			Block.makeCuboidShape(12, 9, 10, 13, 10, 11),
			Block.makeCuboidShape(12, 7, 10, 13, 8, 11),
			Block.makeCuboidShape(5, 9, 12, 6, 10, 13),
			Block.makeCuboidShape(5, 7, 12, 6, 8, 13),
			Block.makeCuboidShape(3, 9, 5, 4, 10, 6),
			Block.makeCuboidShape(3, 7, 5, 4, 8, 6),
			Block.makeCuboidShape(12, 9, 5, 13, 10, 6),
			Block.makeCuboidShape(12, 7, 5, 13, 8, 6),
			Block.makeCuboidShape(6, 8, 5, 16, 9, 11)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	private static final VoxelShape SHAPE_W = Stream.of(
			Block.makeCuboidShape(13, 8, 5.000000000000002, 14, 9, 11),
			Block.makeCuboidShape(2, 8, 5, 3, 9, 11),
			Block.makeCuboidShape(12, 8, 4, 13, 9, 5),
			Block.makeCuboidShape(3, 8, 11, 4, 9, 12),
			Block.makeCuboidShape(12, 8, 11, 13, 9, 12),
			Block.makeCuboidShape(3, 8, 4, 4, 9, 5),
			Block.makeCuboidShape(11, 8, 3, 12, 9, 4),
			Block.makeCuboidShape(4, 8, 12, 5, 9, 13),
			Block.makeCuboidShape(11, 8, 12, 12, 9, 13),
			Block.makeCuboidShape(4, 8, 3, 5, 9, 4),
			Block.makeCuboidShape(5, 8, 13, 11, 9, 14),
			Block.makeCuboidShape(5, 8, 2, 11, 9, 3),
			Block.makeCuboidShape(6, 0, 6, 10, 9, 10),
			Block.makeCuboidShape(4, 7, 7, 6, 8, 9),
			Block.makeCuboidShape(7, 7, 4, 9, 8, 6),
			Block.makeCuboidShape(10, 7, 7, 12, 8, 9),
			Block.makeCuboidShape(7, 7, 10, 9, 8, 12),
			Block.makeCuboidShape(3, 9, 6, 4, 10, 10),
			Block.makeCuboidShape(3, 7, 6, 4, 8, 10),
			Block.makeCuboidShape(6, 9, 12, 10, 10, 13),
			Block.makeCuboidShape(6, 7, 12, 10, 8, 13),
			Block.makeCuboidShape(6, 9, 3, 10, 10, 4),
			Block.makeCuboidShape(6, 7, 3, 10, 8, 4),
			Block.makeCuboidShape(12, 9, 6, 13, 10, 10),
			Block.makeCuboidShape(12, 7, 6, 13, 8, 10),
			Block.makeCuboidShape(5, 9, 3, 6, 10, 4),
			Block.makeCuboidShape(5, 7, 3, 6, 8, 4),
			Block.makeCuboidShape(12, 9, 5, 13, 10, 6),
			Block.makeCuboidShape(12, 7, 5, 13, 8, 6),
			Block.makeCuboidShape(10, 9, 12, 11, 10, 13),
			Block.makeCuboidShape(10, 7, 12, 11, 8, 13),
			Block.makeCuboidShape(5, 9, 12, 6, 10, 13),
			Block.makeCuboidShape(5, 7, 12, 6, 8, 13),
			Block.makeCuboidShape(4, 9, 4, 5, 10, 5),
			Block.makeCuboidShape(4, 7, 4, 5, 8, 5),
			Block.makeCuboidShape(11, 9, 4, 12, 10, 5),
			Block.makeCuboidShape(11, 7, 4, 12, 8, 5),
			Block.makeCuboidShape(11, 9, 11, 12, 10, 12),
			Block.makeCuboidShape(11, 7, 11, 12, 8, 12),
			Block.makeCuboidShape(4, 9, 11, 5, 10, 12),
			Block.makeCuboidShape(4, 7, 11, 5, 8, 12),
			Block.makeCuboidShape(3, 9, 5, 4, 10, 6),
			Block.makeCuboidShape(3, 7, 5, 4, 8, 6),
			Block.makeCuboidShape(10, 9, 3, 11, 10, 4),
			Block.makeCuboidShape(10, 7, 3, 11, 8, 4),
			Block.makeCuboidShape(12, 9, 10, 13, 10, 11),
			Block.makeCuboidShape(12, 7, 10, 13, 8, 11),
			Block.makeCuboidShape(3, 9, 10, 4, 10, 11),
			Block.makeCuboidShape(3, 7, 10, 4, 8, 11),
			Block.makeCuboidShape(0, 8, 5, 10, 9, 11)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
		public SpecalBlock(Properties properties) 
		{
			super(properties);
			this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
			
		}
		
		@Override
		public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
		{
			switch(state.get(FACING)) 
			{
			case NORTH:
				return SHAPE_N;
			case SOUTH:
				return SHAPE_S;
			case EAST:
				return SHAPE_E;
			case WEST:
				return SHAPE_W;
			default:
				return SHAPE_N;
			}
		}
		
		@Override
		public BlockState getStateForPlacement(BlockItemUseContext context) 
		{
			return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
		}
		
		@Override
		public BlockState rotate(BlockState state, Rotation rot) 
		{
			return state.with(FACING, rot.rotate(state.get(FACING)));
		}
		
		@Override
		public BlockState mirror(BlockState state, Mirror mirrorIn) 
		{
			return state.rotate(mirrorIn.toRotation(state.get(FACING)));
		}
		
		@Override
		protected void fillStateContainer(Builder<Block, BlockState> builder) 
		{
			builder.add(FACING);
		}
		
		@Override
		public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,Hand handIn, BlockRayTraceResult hit) 
		{
			if(!worldIn.isRemote()) 
			{
				ServerWorld serverWorld = (ServerWorld)worldIn;
				LightningBoltEntity entity = new LightningBoltEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), false);
				serverWorld.addLightningBolt(entity);
			}
			return ActionResultType.SUCCESS;
		}
}
