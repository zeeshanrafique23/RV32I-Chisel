package MyCore
import chisel3.iotesters.PeekPokeTester

class TypeDecode_tb (c: TypeDecode) extends PeekPokeTester(c){

    poke (c.io.opcode,12)
    step(1)
    expect(c.io.rtype,true)
    expect(c.io.stype,false)
}