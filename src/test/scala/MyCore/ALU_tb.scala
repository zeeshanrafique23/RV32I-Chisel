package MyCore
import chisel3.iotesters.PeekPokeTester

class ALU_tb (c:ALU) extends PeekPokeTester(c){

    poke(c.io.oprndA,0xffffffff)
    poke(c.io.oprndB,0x00000002)
    poke(c.io.aluControl,0)
    step (1)
    expect(c.io.aluOUT,0x00000001 )
}