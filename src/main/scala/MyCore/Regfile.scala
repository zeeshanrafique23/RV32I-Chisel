package MyCore 
import chisel3._
import chisel3.util._

class Regfile extends Module{
  val io = IO (new Bundle {
	val regWrite = Input(UInt(1.W))
	val rs1_s = Input(UInt(5.W))
	val rs2_s = Input(UInt(5.W))
	val rd = Input(UInt(5.W))
	val writeData = Input(SInt(32.W))
	val rs1 = Output(SInt(32.W))
	val rs2 = Output(SInt(32.W))
	val regout = Output(SInt(32.W))
  })
	val register = RegInit(VecInit(Seq.fill(32)(0.S(32.W))))
	register(0) := 0.S
	io.rs1 := register(io.rs1_s)
	io.rs2 := register(io.rs2_s)
	when(io.regWrite === 1.U){
		when(io.rd === "b00000".U){register(io.rd) := 0.S}
		.otherwise {
			register(io.rd) := io.writeData
			}
	}

	io.regout := register(7)
}