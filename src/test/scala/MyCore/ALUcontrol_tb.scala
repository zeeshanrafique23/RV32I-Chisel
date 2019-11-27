package MyCore
import chisel3.iotesters.PeekPokeTester

class ALUcontrol_tb (c:ALUcontrol) extends PeekPokeTester(c){

    poke(c.io.aluOP,1)
    poke(c.io.fun_3,1)

    step (1)

    expect(c.io.aluControl,1)
}