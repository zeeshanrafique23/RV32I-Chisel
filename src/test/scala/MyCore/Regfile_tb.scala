package MyCore

import chisel3.iotesters.PeekPokeTester

class Regfile_tb (c:Regfile) extends PeekPokeTester(c) {
   
   // poke(c.io.rs1_s,3)
   // poke(c.io.rs2_s, 2)
   // poke(c.io.regWrite, 1)
	//poke(c.io.rd, 4)
	//poke(c.io.writeData, 4)
    step(1)
    //expect(c.io.rs2, 0)
	//expect(c.io.rs1, 0)
}