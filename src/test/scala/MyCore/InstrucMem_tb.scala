package MyCore
import chisel3.iotesters.PeekPokeTester

class InstrucMem_tb (c:InstrucMem) extends  PeekPokeTester(c){

    poke(c.io.address,0)
    step(1)
}