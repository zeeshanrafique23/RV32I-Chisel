package MyCore
import chisel3.iotesters.PeekPokeTester

class Control_tb (c:Control) extends PeekPokeTester(c){

    poke(c.io.opcode,0)
    step(1)
}