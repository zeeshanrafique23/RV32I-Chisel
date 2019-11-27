package MyCore
import chisel3.iotesters.PeekPokeTester

class ControlDecode_tb (c:ControlDecode) extends PeekPokeTester(c){

    poke(c.io.branch,1)
    step(1)
}