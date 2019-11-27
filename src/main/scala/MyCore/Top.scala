package MyCore 
import chisel3._
import  chisel3.util._

class Top extends Module{
    val io = IO(new Bundle{
        val reg_5 = Output(SInt(32.W))
    })

    val i_mem   = Module(new InstrucMem())
    val d_mem   = Module(new DataMem())
    val control = Module(new Control())
    val immGen  = Module(new ImmGen())
    val alu     = Module(new ALU())
    val aluCntrl= Module(new ALUcontrol())
    val regfile = Module(new Regfile())
    val jalr    = Module(new Jalr())
    val pc      = Module(new PC())

    
    // INSTRUCTION MEMORY
    i_mem.io.address := pc.io.out >> 2
    val instruction = i_mem.io.instruction
    
    // CONTROL
    control.io.opcode := instruction(6,2)


    //val delay = RegInit(0.U(32.W))
    //delay := pc.io.out                   /////////////////////////////////////////////////////////////////////////////

    //IMMEDIATE GENERATION
    immGen.io.instruction := instruction
    //immGen.io.pc  := delay
    immGen.io.pc  := pc.io.out


    //REGISTER FILE
    regfile.io.rs1_s := instruction(19,15)
    regfile.io.rs2_s := instruction(24,20)
    regfile.io.rd    := instruction(11,7)
    regfile.io.regWrite := control.io.regWrite

    // ALU CONTROL
    aluCntrl.io.fun_3 := instruction(14,12)
    aluCntrl.io.fun_7 := instruction(30)
    aluCntrl.io.aluOP := control.io.aluOP

    // ALU
    alu.io.aluControl := aluCntrl.io.aluControl

    // OPERAND A MUX
    when(control.io.oprndAsel === "b00".U){
        alu.io.oprndA := regfile.io.rs1
    
    }.elsewhen(control.io.oprndAsel === "b01".U){
        alu.io.oprndA := (pc.io.out).asSInt
    
    }.elsewhen(control.io.oprndAsel === "b10".U){
        alu.io.oprndA := (pc.io.out).asSInt

    }.otherwise{
        alu.io.oprndA := regfile.io.rs1
    }

   // OPERAND B MUX
   when(control.io.oprndBsel === "b1".U){

    when(control.io.immSel === "b00".U){
       alu.io.oprndB := immGen.io.iImm_out

    }.elsewhen(control.io.immSel === "b01".U){
       alu.io.oprndB := (immGen.io.sImm_out).asSInt

    }.elsewhen(control.io.immSel === "b10".U){
       alu.io.oprndB := (immGen.io.uImm_out).asSInt 

    }.otherwise{
        alu.io.oprndB := 0.S(32.W)
    }
   } .otherwise{
       alu.io.oprndB := regfile.io.rs2
   }  
   


   //DATA MEMORY
    d_mem.io.writeEn := control.io.memWrite
    d_mem.io.readEn  := control.io.memRead
    d_mem.io.dataIn  := regfile.io.rs2
    d_mem.io.address := (alu.io.aluOUT(11,2)).asUInt

   // WRITE BACK
   when(control.io.memtoReg === "b1".U){
       regfile.io.writeData := (d_mem.io.dataOut).asSInt
   
   }.otherwise{
       regfile.io.writeData := alu.io.aluOUT

   }
    // PC MUX
   when (alu.io.branch ==="b1".U && control.io.branch === "b1".U && control.io.nextPCsel === "b001".U){
        pc.io.in := immGen.io.sbImm_out

   }.elsewhen(control.io.nextPCsel === "b010".U){
        pc.io.in := immGen.io.ujImm_out

   }.elsewhen(control.io.nextPCsel === "b011".U){
        pc.io.in := jalr.io.out

   }.otherwise{
        pc.io.in := pc.io.out + 4.U(32.W)

   }
  
   // JALR
   jalr.io.a := regfile.io.rs1
   jalr.io.b := immGen.io.iImm_out


   // OUTPUT REGISTER
   io.reg_5   := regfile.io.regout
 

}